define([
    "dojo/_base/declare",
    "dojo/router",
    "dijit/_WidgetBase",
    "dgrid/OnDemandGrid",
    "dstore/RequestMemory"
], function(declare, router, _WidgetBase, OnDemandGrid, RequestMemory) {

    return declare([_WidgetBase], {

        constructor(args, attachPoint){
            this.grid = new OnDemandGrid({
                columns: this.columns,
                collection: this.store
            }, attachPoint);

        },

        columns : [
            {
                field: "corporationName",
                label: "Corporation Name"
            },
            {
                field: "highestAskPrice",
                label: "Highest Ask Price"
            },
            {
                field: "lowestBidPrice",
                label: "Lowest Bid Price"
            }
        ],

        store: new RequestMemory({
            target: "resources/stocks.json"
        }),

        startup: function() {

            let grid = this.grid;
            grid.on(".dgrid-content .dgrid-row:dblclick", function (event) {
                let selectedStock = grid.row(event).data;
                router.go("/stocks/" + selectedStock.id + "/orders");
            });

            grid.startup();
        }
    });

});