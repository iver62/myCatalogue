var app=angular.module("MyApp", ['ui.router']);

app.config(function($stateProvide, $urlRouterProvider) {
	$stateProvider.state("produits", {
		url: "/produits",
		templateUrl: "views/produits.html",
		controller: "ProduitController"
	});
	$stateProvider.state("categories", {
		url: "/categories",
		templateUrl: "views/categories.html",
		controller: "CategorieController"
	});
	$urlRouterProvider.otherwise("produits");
});

app.controller("CategorieController", function() {
	
});

app.controller("ProduitController", function($scope, $http) {
	$scope.pageProduits=null;
	$scope.motCle="";
	$scope.pageCourante=0;
	$scope.size=5;
	$scope.pages=[];
	
	$scope.chercher = function() {
		$http.get("http://localhost:8080/chercher?mc="+$scope.motCle+"&page="+$scope.pageCourante+"&size="+$scope.size)
			.then(function(response) {
				$scope.pageProduits = response.data;
				$scope.pages = new Array(response.data.totalPages);
			}, function error() {
				console.log("error");
	    });
	}
	
	$scope.gotoPage=function(p) {
		$scope.pageCourante = p;
		$scope.chercher();
	}
});