define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dijit/form/Button",
    "stocksim/order/OrdersGrid/OrdersGrid",
    "dojo/text!stocksim/util/header/ApplicationHeader.html"
], function(declare, _WidgetBase, _TemplatedMixin, Button, OrdersGrid,  template) {

    return declare([_WidgetBase, _TemplatedMixin], {

        templateString: template,

        startup: function () {

            this.userName.innerHTML = this.contextData.currentUser.name;

            let loginButon = new Button({
                label: this.contextData.currentUser.token ? "Logout" : "Login"
            }, this.loginButton)
            loginButon.startup();

        }
    });

});