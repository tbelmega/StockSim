define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dgrid/OnDemandGrid",
    "dstore/RequestMemory",
    "dojo/text!stocksim/stock/StockPage/StockPage.html"
], function(declare, _WidgetBase, _TemplatedMixin, OnDemandGrid, RequestMemory,  template) {

    return declare([_WidgetBase, _TemplatedMixin], {

        templateString: template,

        startup: function () {
            console.log("list orders");
        }
    });

});