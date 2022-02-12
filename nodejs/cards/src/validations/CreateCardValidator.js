const validator = require("express-validator");

const validate = {
    check() {
        return [
            validator.check("cardNumber").notEmpty().withMessage("cardNumber cannot be blank").isInt().withMessage("cardNumber should be a number"),
            validator.check("embossName").notEmpty().withMessage("embossName cannot be blank"),
            validator.check("customerName")
            .notEmpty()
            .withMessage("customerName cannot be blank"),
            validator.check("documentNumber").notEmpty().withMessage("documentNumber cannot be blank").isInt().withMessage("documentNumber should be a number"),
            validator.check("motherName").notEmpty().withMessage("motherName cannot be blank"),
            validator.check("address").notEmpty().withMessage("address cannot be blank"),
            validator.check("city").notEmpty().withMessage("city cannot be blank")
        ];
    },

    resultsValidator(req) {
        const messages = [];
        if (!validator.validationResult(req).isEmpty()) {
            const errors = validator.validationResult(req).array();
            for (const i of errors) {
                messages.push(i);
            }
        }
        return messages;
    },
};

module.exports = {
    validate
}