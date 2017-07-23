define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/_TemplatedMixin",
    "dojo/parser",
    "dijit/form/Button",
    "stocksim/order/OrdersGrid/OrdersGrid",
    "dojo/text!stocksim/stock/StockPage/StockPage.html"
], function(declare, _WidgetBase, _WidgetsInTemplateMixin,_TemplatedMixin, parser, Button, OrdersGrid,  template) {

    return declare([_WidgetBase, _WidgetsInTemplateMixin, _TemplatedMixin], {

        constructor(args){
            this.corporationId = args.id;
        },

        templateString: template,

        startup: function () {
            console.log(this.createOrderButtonAttachPoint);

            let asksGrid = new OrdersGrid({"corporationId":this.corporationId, "orderType":"ask"}, this.asksGridAttachPoint);
            asksGrid.startup();
            let bidsGrid = new OrdersGrid({"corporationId":this.corporationId, "orderType":"bid"}, this.bidsGridAttachPoint);
            bidsGrid.startup();
        }
    });

});