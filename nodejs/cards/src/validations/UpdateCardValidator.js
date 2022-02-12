const validator = require("express-validator");

const validate = {
    check() {
        return [
            validator.check("cardNumber").optional().isInt().withMessage("cardNumber should be a number"),
            validator.check("documentNumber").optional().isInt().withMessage("documentNumber should be a number"),
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