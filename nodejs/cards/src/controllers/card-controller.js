const neDB = require('../configurations/database');
const createValidate = require("../validations/CreateCardValidator");
const updateValidate = require("../validations/UpdateCardValidator");
const api = {}

api.findAll = (request, response) => {
    neDB.find({}).sort({
        customerName: 1
    }).exec((err, cards) => {
        if (err) {
            response.status(500).send({
                error: "Internal server error"
            });
        } else if (!cards || cards.length == 0) {
            response.status(404).send({
                error: "No cards found"
            });
        } else {
            response.status(200).json(cards)
        }
    })
}

api.save = (request, response) => {
    const canonical = request.body;

    const errors = createValidate.validate.resultsValidator(request);

    if (errors.length > 0) {
        return response.status(409).json({
            error: errors,
        });
    }

    neDB.insert(canonical, (err, card) => {
        if (err) {
            response.status(500).send({
                error: "Internal server error"
            });
        } else {
            response.status(201).send(card);
        }
    })
}

api.updateOne = (request, response) => {
    const id = request.params._id;
    const canonical = request.body;

    const errors = updateValidate.validate.resultsValidator(request);

    if (errors.length > 0) {
        return response.status(409).json({
            error: errors,
        });
    }

    neDB.update({
        _id: id
    }, {
        $set: {
            ...canonical
        },
    }, {
        returnUpdatedDocs: true
    }, (err, card, affectedDocuments) => {

        if (err) {
            response.status(500).send({
                error: "Internal server error"
            });
        } else if (!card) {
            response.status(404).send({
                error: "Card not found"
            });
        } else {
            response.status(200).json(affectedDocuments)
        }
    });
}

api.deleteOne = (request, response) => {
    const id = request.params._id
    neDB.remove({
        _id: id
    }, (err, card) => {

        if (err) {
            response.status(500).send({
                error: "Internal server error"
            });
        } else if (!card) {
            response.status(404).send({
                error: "Card not found"
            });
        } else {
            response.status(200).send({
                success: "Card has been deleted"
            })
        }
    })
}

api.findOne = (request, response) => {
    const id = request.params._id;

    neDB.find({
        _id: id
    }, (err, card) => {
        if (err) {
            response.status(500).send({
                error: "Internal server error"
            });
        } else if (!card || card.length == 0) {
            response.status(404).send({
                error: "Card not found"
            });
        } else {
            response.status(200).send(card);
        }
    })
}

api.pagination = (request, response) => {
    const page = request.query.page;
    neDB.find({}).sort({
        customerName: 1
    }).skip(page ? 10 * (page) : 0).limit(10).exec((err, cards) => {
        if (err) {
            response.status(500).send({
                error: "Internal server error"
            });
        } else if (!cards || cards.length == 0) {
            response.status(404).send({
                error: "No cards found on this page"
            });
        } else {
            response.status(200).json(cards)
        }
    })
}

module.exports = api