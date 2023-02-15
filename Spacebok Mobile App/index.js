import {AppRegistery} from 'react-native';
import App from './App';
import { name  as Appname} from './app.json';
AppRegistery.registerComponent (Appname ,() => App);


const express = require('./config/express');

const app = express();


app.listen(3333, function() {
    console.log('Listening on port: 3333');
});