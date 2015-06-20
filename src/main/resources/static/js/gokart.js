angular.module('gokart', [ 'ngRoute' ])
    .config(function($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl : 'home.html',
            controller : 'home'
        }).when('/login', {
            templateUrl : 'login.html',
            controller : 'navigation'
        }).when('/addDriver', {
            templateUrl : 'addDriver.html',
            controller : 'driverController'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    .controller('home', function($scope, $http, $location) {
        if (!$scope.authenticated) {
            $location.path("/login");
        }

        $http.get('http://localhost:8080/drivers')
            .success(function(data) {
                $scope.drivers = data;
            });

        $scope.addDriver = function() {
            $location.path("/addDriver");
        };
    })
    .controller('driverController', function($rootScope, $scope, $http, $location) {
        $scope.driverModel = {};
        $scope.createDriver = function() {
            $http.post("http://localhost:8080/drivers/add", $scope.driverModel)
                .success(function(data) {
                    $location.path("/");
                });
        }
    })
    .controller('navigation', function($rootScope, $scope, $http, $location) {

        var authenticate = function(credentials, callback) {

            var headers = credentials ? {authorization : "Basic "
            + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('http://localhost:8080/user', {headers : headers}).success(function(data) {
                if (data.name) {
                    $rootScope.authenticated = true;
                    $http.get('http://localhost:8080/drivers/self').success(function(data) {
                        $rootScope.user = data;
                    });
                } else {
                    $rootScope.authenticated = false;
                }
                callback && callback();
            }).error(function() {
                $rootScope.authenticated = false;
                callback && callback();
            });

        }

        authenticate();

        $scope.credentials = {};
        $scope.login = function() {
            authenticate($scope.credentials, function() {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    $scope.error = false;
                } else {
                    $location.path("/login");
                    $scope.error = true;
                }
            });
        };

        $scope.logout = function() {
            $http.post('http://localhost:8080/logout', {}).success(function() {
                $rootScope.authenticated = false;
                $location.path("/login");
            }).error(function(data) {
                $rootScope.authenticated = false;
            });
        }
    });