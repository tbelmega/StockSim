require([
    'dojo/dom',
    'dojo/dom-construct',
    "dojo/router",
    "dijit/form/Button",
    "stocksim/pages/list-stocks-page/ListStocksPage"
], function (dom, domConstruct, router, Button, ListStocksPage) {

    router.register("/list-stocks", function (event) {
        let myPage = new ListStocksPage({}, "main-content");
        myPage.startup();
    });
    router.startup();

    router.go("/list-stocks");

});