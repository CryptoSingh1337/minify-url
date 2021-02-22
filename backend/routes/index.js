const router = require('express').Router();
const indexController = require('../controller/indexController');

router.post('/', indexController.handleURL);

router.get('/:key', indexController.handleRedirect);

module.exports = router;