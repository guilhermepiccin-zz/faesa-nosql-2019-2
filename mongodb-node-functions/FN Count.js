const mongodb = require('mongodb');

const uri = '<MONGODB URI>';

// May be retained between function executions depending on whether Azure
// cleans up memory
let client = null;

module.exports = function (context, req) {
  context.log('Running');

  let hasClient = client != null;

  if (client == null) {
    mongodb.MongoClient.connect(uri, function(error, _client) {
      if (error) {
        context.log('Failed to connect');
        context.res = { status: 500, body: res.stack }
        return context.done();
      }
      client = _client;
      context.log('Connected');
      query();
    });
  } else {
    query();
  }

  function query() {
    client.db('<MONGODB DATABASE NAME>').collection('<MONGODB COLLECTION NAME>').find().toArray(function(error, docs) {
      if (error) {
        context.log('Error running query');
        context.res = { status: 500, body: res.stack }
        return context.done();
      }

      context.log('Success!');
      context.res = {
        headers: { 'Content-Type': 'application/json' },
        body: 'Total of ' +  docs.length + ' documents.'
      };
      context.done();     
    });
  }
};