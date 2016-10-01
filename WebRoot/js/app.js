var app = angular.module('icase', ['ngRoute']);

// ====================================================================================

app.config(function($routeProvider) {
	$routeProvider.when('/home', {
		templateUrl: 'template/home.html'
	}).when('/paper', {
		templateUrl: 'template/paper.html',
		controller: 'PaperController'
	}).when('/personal', {
		templateUrl: 'template/personal.html',
		controller: 'UserController'
	}).when('/about', {
		templateUrl: 'template/about.html'
	}).when('/help', {
		templateUrl: 'template/help.html'
	}).when('/review', {
		templateUrl: 'template/review.html',
		controller: 'PaperController'
	}).when('/login', {
		templateUrl: 'template/login.html'
	}).when('/register', {
		templateUrl: 'template/register.html',
		controller: 'UserController'
	}).when('/submission/1', {
		templateUrl: 'template/submit/submission_step_1.html'
	}).when('/submission/2', {
		templateUrl: 'template/submit/submission_step_2.html'
	}).when('/submission/3', {
		templateUrl: 'template/submit/submission_step_3.html',
		controller: 'PaperController'
	}).when('/submission/4', {
		templateUrl: 'template/submit/submission_step_4.html',
		controller: 'AuthorController'
	}).when('/submission/5', {
		templateUrl: 'template/submit/submission_step_5.html',
		controller: 'PaperController'
	}).otherwise({
		redirectTo: '/home'
	});
});

// ================================================================================================

app.filter('paperStatus', function() {

	return function(input) {

		var res = '未知';

		switch (input) {
			case -100:
				{
					res = '已撤销';
					break;
				}
			case 100:
				{
					res = '已提交';
					break;
				}
			case 200:
				{
					res = '等待审阅';
					break;
				}
			case 300:
				{
					res = '编辑部审稿';
					break;
				}
		}

		return res;
	}
});

// ===================================================================================================

app.value('submission', {
	paper: {},
	authors: []
})

// ===================================================================================================

var pageController = app.controller('PageController', function($scope, $http, $location) {

	$scope.loginStatus = false;
	$scope.user = {};

	$http.post('user/login_status').success(function(response) {
		if (response.code === 200) {
			$scope.loginStatus = response.content.loginStatus;

			if ($scope.loginStatus) {
				$scope.user = response.content.user;
			}
		}
	});

	$scope.tools = {
		goto: function(path) {
			$location.path(path);
		}
	}

	$scope.login = function() {
		$http({
			method: 'POST',
			url: 'user/login',
			params: {
				'name': $scope.user.name,
				'password': $scope.user.password
			}
		}).success(function(response) {
			if (response.code === 200) {
				$scope.loginStatus = true;
				$scope.user = response.content;

				$location.path('home');
			}
		});
	};

	$scope.logout = function() {
		$http.post('user/logout').success(function(response) {
			if (response.code === 200) {
				$scope.loginStatus = false;
				$scope.user = {};

				$location.path('home');
			}
		});
	};


});

var userController = app.controller('UserController', function($scope, $http, $location) {

	$scope.user_all = [];
	$scope.user_current = {};
	$scope.user = {};

	$scope.getAllUsers = function() {
		$http.post('user/list_all').success(function(response) {
			if (response.code === 200) {
				$scope.user_all = response.content;
			}
		});
	};

	$scope.getUser = function(id) {}

	$scope.getCurrentUser = function() {
		$http.post('user/information').success(function(response) {
			if (response.code === 200) {
				$scope.user_current = response.content;
			}
		});
	};

	$scope.addUser = function() {
		$http({
			method: 'POST',
			url: 'user/add',
			params: {
				'name': $scope.user.name,
				'password': $scope.user.password,
				'email': $scope.user.email,
				'tel': $scope.user.tel
			}
		}).success(function(response) {
			if (response.code === 200) {
				alert('注册成功');
				$location.path('login');
			}
		});
	};

	$scope.delUser = function() {};
	$scope.updateUser = function() {};
});

var paperController = app.controller('PaperController', function($scope, $http, $location, submission) {

	$scope.paper_all = [];
	$scope.paper_current_user = [];
	$scope.paper_user = [];
	$scope.paper_review = [];
	$scope.paper = {};

	$scope.submission = submission;

	console.log(submission);

	$scope.getAllPapers = function() {
		$http.post('paper/list_all').success(function(response) {
			if (response.code === 200) {
				$scope.paper_all = response.content;
			}
		});
	};

	$scope.getCurrentUserPapers = function() {
		$http.post('paper/list_user').success(function(response) {
			if (response.code === 200) {
				$scope.paper_current_user = response.content;
			}
		});
	};

	$scope.updatePaperStatus = function(id, status) {
		$http({
			method: 'POST',
			url: 'paper/update_paper_status',
			params: {
				'paperId': id,
				'status': status
			}
		}).success(function(response) {
			if (response.code === 200) {
				$scope.getCurrentUserPapers();
			}

			if(status === 300){
				alert('Submit success!');
				$location.path('paper');
			}
		});
	}

	$scope.getReviewPapers = function(){
		$http.post('paper/list_review').success(function(response){
			if(response.code === 200){
				$scope.paper_review = response.content;
			}
		});
	}

	$scope.getPaper = function(id) {};
	$scope.addPaper = function() {
		$http({
			method: 'POST',
			url: 'paper/add',
			params: {
				'title': $scope.paper.title,
				'classification': $scope.paper.classification,
				'type': $scope.paper.type,
				'keys': $scope.paper.keys,
				'abstract': $scope.paper.abstract,
				'remark': $scope.paper.remark,
				'file': $('#fileName').val()
			}
		}).success(function(response) {
			if (response.code === 200) {
				submission.paper = response.content;
				$location.path('submission/4');
			}
		});
	};

	$scope.delPaper = function() {};
	$scope.updatePaper = function() {};
});

var authorController = app.controller('AuthorController', function($scope, $http, $location, submission) {

	$scope.author_all = [];
	$scope.author_paper = [];
	$scope.author_user = [];
	$scope.authors = [];

	var Author = function () {
        this.name = '';
        this.address = '';
        this.country = '';
        this.gender = 'Male';
        this.email = '';
        this.tel = '';
        this.type = '';
    };

    $scope.authors.push(new Author());

	$scope.getAllAuthors = function() {};
	$scope.getUserAuthors = function() {};
	$scope.getPaperAuthors = function() {};

	$scope.getAuthor = function() {};

	$scope.addAuthor = function(){
		for(var i=0; i<$scope.authors.length; i++){
            $http({
                method: 'POST',
                url: 'author/add',
                params: {
                    'name': $scope.authors[i].name,
                    'address': $scope.authors[i].classification,
                    'country': $scope.authors[i].country,
                    'gender': $scope.authors[i].gender,
                    'email': $scope.authors[i].email,
                    'tel': $scope.authors[i].tel,
                    'authorType': $scope.authors[i].type,
                    'order': i,
                    'paperId': submission.paper.id
                }
            });
        }

        submission.authors = $scope.authors;
        $location.path('submission/5');
	}
	
	$scope.pushAuthor = function() {
		$scope.authors.push(new Author());
	}
});

var commitController = app.controller('CommitController', function($scope, $http, $location) {

});
