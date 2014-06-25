var URL = casper.cli.get('home');

casper.test.begin('test application user module', 1, function suite(test) {
	casper.start(URL + "#/users", function() {        
		test.assertTextExists('Users', 'body contains users"');
    });
	
	casper.run(function() {
        test.done();
    });	
});