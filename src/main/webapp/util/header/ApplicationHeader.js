define([
    "dojo/_base/declare",
    "dijit/_WidgetBase",
    "dijit/_TemplatedMixin",
    "dojox/mvc/at",
    "stocksim/util/header/ApplicationHeaderModel",
    "dijit/form/Button",
    "stocksim/order/OrdersGrid/OrdersGrid",
    "dojo/text!stocksim/util/header/ApplicationHeader.html"
], function (declare, _WidgetBase, _TemplatedMixin, at, ApplicationHeaderModel, Button, OrdersGrid, template) {

    return declare([_WidgetBase, _TemplatedMixin], {

        constructor() {
            this.model = new ApplicationHeaderModel();
        },

        templateString: template,

        startup: function () {

            this.model.watch("userName", () => {
                this.userName.innerHTML = this.model.userName;
            });

            let loginFunction = () => {
                contextData.currentUser = contextData.currentUser.token ? {} : contextData.testUser;
                this.model.set("userName", contextData.currentUser.name || "");
                this.model.set("buttonLabel", contextData.currentUser.token ? "Logout" : "Login");
            }

            let loginButon = new Button({
                label: at(this.model, "buttonLabel"),
                onClick: loginFunction
            }, this.loginButton)
            loginButon.startup();

        }
    });

});