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

            let user = this.contextData.currentUser;
            this.userName.innerHTML = user.name;

            let loginButon = new Button({
                label: user.token ? "Logout" : "Login",
                onclick: user.token ? this.contextData.currentUser = {} :
                    this.contextData.currentUser = this.contextData.testUser
            }, this.loginButton)
            loginButon.startup();

        }
    });

});