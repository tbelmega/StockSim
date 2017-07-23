require([
    'dojo/dom',
    'dojo/dom-construct',
    "dojo/router",
    "stocksim/util/header/ApplicationHeader"
], function (dom, domConstruct, router, ApplicationHeader) {

    let contextData = {
        currentUser: {
            name: "C.Norris",
            id: "c.norris@gmail.com",
            token: "123456789"
        }
    }

    let applicationHeader = new ApplicationHeader({contextData: contextData}, "application-header");

    applicationHeader.startup();

    let currentPage;

    // unregisteres the current page widget and destroys its dom node. create new page node and start target page
    function startupPage(pageLocation, params) {
        if (currentPage) currentPage.destroy();
        dom.byId("main-content").innerHTML = "";

        // require loads the target page widget from the given location, creates page node and starts the page.
        require([pageLocation], function (Page) {
            let pageNode = domConstruct.create("div", {}, "main-content");
            currentPage = new Page(params, pageNode);
            currentPage.startup();
        });
    }

    router.register("/stocks", function (event) {
        startupPage("stocksim/stock/StocksListPage/StocksListPage");
    });

    router.register("/stocks/:id/orders", function (event) {
        startupPage("stocksim/stock/StockPage/StockPage", event.params); // pass URL param to page
    });

    router.startup();
    router.go("/stocks");
});