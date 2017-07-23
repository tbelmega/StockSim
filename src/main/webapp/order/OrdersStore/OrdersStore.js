define([
    "dojo/_base/declare",
    "dstore/RequestMemory",
    "dstore/Trackable"
], function (declare, RequestMemory, Trackable) {

    return declare([RequestMemory, Trackable], {
        target: "order/OrdersStore/orders.json"
    });

});