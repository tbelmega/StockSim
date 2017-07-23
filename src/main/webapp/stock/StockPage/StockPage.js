define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dijit/form/Button",
    "stocksim/order/OrdersGrid/OrdersGrid",
    "dojo/text!stocksim/stock/StockPage/StockPage.html"
], function(declare, _WidgetBase, _TemplatedMixin, Button, OrdersGrid,  template) {

    return declare([_WidgetBase, _TemplatedMixin], {

        constructor(args){
            this.corporationId = args.id;
        },

        templateString: template,

        startup: function () {
            let asksGrid = new OrdersGrid({"corporationId":this.corporationId, "orderType":"ask"}, this.asksGridAttachPoint);
            asksGrid.startup();
            let bidsGrid = new OrdersGrid({"corporationId":this.corporationId, "orderType":"bid"}, this.bidsGridAttachPoint);
            bidsGrid.startup();

            let createOrderButton = new Button({
                label: "Create Order"
            }, this.createOrderButtonAttachPoint);
        }
    });

});