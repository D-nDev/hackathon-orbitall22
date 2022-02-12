const api = require('../controllers/card-controller');
const checkCreate = require('../validations/CreateCardValidator');
const checkUpdate = require('../validations/UpdateCardValidator');

module.exports = (app) => {
    app.route('/cards/paginationAndSorting')
        .get(api.pagination)

    app.route('/cards')
        .get(api.findAll)
        .post(checkCreate.validate.check(), api.save)

    app.route('/cards/:_id')
        .get(api.findOne)

    app.route('/cards/:_id')
        .delete(api.deleteOne)

    app.route('/cards/:_id')
        .put(checkUpdate.validate.check(), api.updateOne)
}