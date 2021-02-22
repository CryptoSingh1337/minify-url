const md5 = require('md5');

const URL = require('../model/url');

exports.handleURL = function (req, res) {
    const { url } = req.query;
    const key = md5(url).substring(8, 20);
    URL.findOne({ short_url: key }, (err, _url) => {
        if (err) {
            res.status(500).json(`Error: ${err.message}`);
        } else {
            if (!_url) {
                new URL({
                    original_url: url,
                    short_url: key
                }).save()
                    .then(() => {
                        URL.findOne({ short_url: key }, (err, __url) => {
                            res.status(200).json({
                                original_url: __url.original_url,
                                short_url: process.env.HOST + __url.short_url
                            });
                        });
                    });
            } else {
                URL.findOne({ short_url: key }, (err, __url) => {
                    res.status(200).json({
                        original_url: __url.original_url,
                        short_url: process.env.HOST + __url.short_url
                    });
                });
            }
        }
    });
}

exports.handleRedirect = function (req, res) {
    const key = req.params.key;
    URL.findOne({ short_url: key }, (err, url) => {
        if (err) {
            res.status(500).json(`Error: ${err.message}`);
        } else if (url == null) {
            res.status(404).json('Not Found');
        } else {
            res.redirect(url.original_url);
        }
    });
}