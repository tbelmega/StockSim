define([
    "dojo/_base/declare",
    "dojo/router",
    "dijit/_WidgetBase",
    "dgrid/OnDemandGrid",
    "stocksim/order/OrdersStore/OrdersStore"
], function (declare, router, _WidgetBase, OnDemandGrid, OrdersStore) {

    return declare([_WidgetBase], {

        constructor(args, attachPoint){
            this.grid = new OnDemandGrid({
                columns: this.columns,
                collection: this.store.filter({
                    corporationId: args.corporationId,
                    orderType: args.orderType
                }).sort([
                    {property: "price", descending: args.orderType === "ask"} // sort asks descending, bids ascending
                ])
            }, attachPoint);
        },

        columns: [
            {
                field: "numberOfShares",
                label: "Number of Shares"
            },
            {
                field: "price",
                label: "Price"
            },
            {
                field: "expiryDateTime",
                label: "Expires at"
            }
        ],

        store: new OrdersStore(),

        startup: function () {

            let grid = this.grid;

            // grid.on(".dgrid-content .dgrid-row:dblclick", function (event) {
            //     let selectedStock = grid.row(event).data;
            //     router.go("/stocks/" + selectedStock.id + "/orders");
            // });

            grid.startup();
        }
    });

});