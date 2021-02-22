const mongoose = require('mongoose');

const urlSchema = new mongoose.Schema({
    original_url: {
        type: String,
        required: true
    },

    short_url: {
        type: String,
        required: true
    }
});

const URL = mongoose.model('url', urlSchema);
module.exports = URL;
