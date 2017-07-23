define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_WidgetsInTemplateMixin",
    "dijit/_TemplatedMixin",
    "dijit/form/Button",
    "stocksim/order/OrdersGrid/OrdersGrid",
    "dojo/text!stocksim/util/header/ApplicationHeader.html"
], function(declare, _WidgetBase, _WidgetsInTemplateMixin,_TemplatedMixin, Button, OrdersGrid,  template) {

    return declare([_WidgetBase, _WidgetsInTemplateMixin, _TemplatedMixin], {

        templateString: template,

        startup: function () {
        }
    });

});