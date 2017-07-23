require([
    'dojo/dom',
    'dojo/dom-construct',
    "dojo/router",
    "dijit/form/Button"
], function (dom, domConstruct, router, Button) {

    let currentPage;

    // unregisteres the current page widget and destroys its dom node. create new page node and start target page
    function startupPage(pageLocation) {
        if (currentPage) currentPage.destroy();

        // require loads the target page widget from the given location, creates page node and starts the page.
        require([pageLocation], function(Page){
            let pageNode = domConstruct.create("div", {}, "main-content");
            currentPage = new Page({}, pageNode);
            currentPage.startup();
        });
    }

    router.register("/stocks", function (event) {
        startupPage("stocksim/stock/StocksListPage/StocksListPage");
    });

    router.register("/stocks/:id/orders", function (event) {
        startupPage("stocksim/stock/StockPage/StockPage");
    });

    router.startup();
    router.go("/stocks");
});