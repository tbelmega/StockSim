define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "stocksim/stock/StocksGrid/StocksGrid",
    "dojo/text!stocksim/stock/StocksListPage/StocksListPage.html"
], function(declare, _WidgetBase, _TemplatedMixin, StocksGrid, template) {

    return declare([_WidgetBase, _TemplatedMixin], {

        templateString: template,

        startup: function() {
            let stocksGrid = new StocksGrid({}, this.gridAttachPoint);
            stocksGrid.startup();
        }
    });

});