require('dotenv').config();
const express = require('express');
const createError = require('http-errors');
const helmet = require('helmet');
const logger = require('morgan');

require('./config/mongo');

const app = express();

app.set('trust proxy', 1);

app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(helmet());

app.use(logger('dev'));

app.use((req, res, next) => {
    res.append('Access-Control-Allow-Origin', ['*']);
    res.append('Access-Control-Allow-Headers', 'Content-Type');
    next();
});

app.use('/', require('./routes/index'));

app.use(function (req, res, next) {
    next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
    res.status(404).json('Not Found');
});

const port = 5000 || process.env.PORT;
app.listen(port, console.log(`Server is running on port: ${port}`));