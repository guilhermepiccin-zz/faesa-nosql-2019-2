const mongodb = require('mongodb');
const assert = require('assert');

const uri = '<MONGODB URL>';

// May be retained between function executions depending on whether Azure
// cleans up memory
let client = null;

module.exports = function (context, req) {
    context.log('Running');

    let hasClient = client != null;

    let itemContent = req.body;

    if (client == null) {
        mongodb.MongoClient.connect(uri, function (error, _client) {
            if (error) {
                context.log('Failed to connect');
                context.res = { status: 500, body: res.stack }
                return context.done();
            }
            client = _client;
            context.log('Connected');
            insert();
        });
    } else {
        insert();
    }

    function insert() {

        client.db('<MONGODB DATABASE NAME>').collection('<MONGODB COLLECTION NAME>').insertOne(itemContent, function (err, r) {
            
            assert.equal(null,err);
            assert.equal(1, r.insertedCount);

            if (err) {
                context.log('Error running insert');
                context.res = { status: 500, body: res.stack }
                return context.done();
            }

            context.log('Success!');
            context.res = {
                headers: { 'Content-Type': 'application/json' },
                body: 'inserted'
            };
            context.done();
        });
    }
};