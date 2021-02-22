require('dotenv').config();
const mongoose = require('mongoose');

mongoose.connect(process.env.URI, {
    useNewUrlParser: true,
    useUnifiedTopology: true,
    useFindAndModify: false
})
.then(console.log('Database connected'))
.catch(err => console.log(err));
