/**
 * INSPINIA - Responsive Admin Theme
 *
 * Inspinia theme use AngularUI Router to manage routing and views
 * Each view are defined as state.
 * Initial there are written state for all view in theme.
 *
 */
function config($stateProvider, $urlRouterProvider, $ocLazyLoadProvider) {
    $urlRouterProvider.otherwise("/home/welcome");

    $ocLazyLoadProvider.config({
        // Set to true if you want to see what and when is dynamically loaded
        debug: false
    });

    $stateProvider
        .state('home', {
            abstract: true,
            url: "/home",
            templateUrl: "content",//common/content.jsp
        })
        .state('home.welcome', {
            url: "/welcome",
            templateUrl: "welcome",
            data: { pageTitle: 'Desarollo Comercial' }
        })
        .state('home.page1', {
            url: "/page1",
            templateUrl: "page1",
            data: { pageTitle: 'Desarollo Comercial' },
            resolve: {
                loadPlugin: function ($ocLazyLoad) {
                    return $ocLazyLoad.load([
                        {
                            serie: true,
                            files: ['static/js/plugins/dataTables/datatables.min.js',
                            	'static/css/plugins/dataTables/datatables.min.css']
                        },
                        {
                            serie: true,
                            name: 'datatables',
                            files: ['static/js/plugins/dataTables/angular-datatables.min.js']
                        },
                        {
                            serie: true,
                            name: 'datatables.buttons',
                            files: ['static/js/plugins/dataTables/angular-datatables.buttons.min.js']
                        },
                        {
                            name: 'ui.switchery',
                            files: ['static/css/plugins/switchery/switchery.css',
                            	'static/js/plugins/switchery/switchery.js',
                            	'static/js/plugins/switchery/ng-switchery.js']
                        }
                    ]);
                }
            }
        })
}
angular
    .module('inspinia')
    .config(config)
    .run(function($rootScope, $state) {
        $rootScope.$state = $state;
    });
