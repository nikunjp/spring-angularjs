var URL = casper.cli.get('home');

casper.test.begin('test application home page', 1, function suite(test) {
	casper.start(URL, function() {
        test.assertTitle("Spring AngularJS", "homepage title as expected");
    });
	
	casper.run(function() {
        test.done();
    });	
});

// to run this - casperjs test testApp_home.js