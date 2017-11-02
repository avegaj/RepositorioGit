/**
 * INSPINIA - Responsive Admin Theme
 *
 */

/**
 * MainCtrl - controller
 */

function MainCtrl() {
	this.userName = 'Eloyus';
    this.helloText = 'Bienvenido a Prometeo';
    this.descriptionText = '¡Vamos a lograrlo!';

};

function datatablesCtrl($scope, $compile, DTOptionsBuilder, DTColumnBuilder) {
    var vm = this;
    vm.message = '';
    vm.editUser = editUser;
    vm.activateUser = activateUser;
    vm.dtInstance = {};
    vm.persons = {};
    vm.activate = '';
    
    vm.dtOptions = DTOptionsBuilder.fromSource('/onlinedc/json/data/entities/')
	    .withPaginationType('full_numbers')
	    .withOption('createdRow', createdRow)
	    .withDOM('lTfgit<"html5buttons"pB>')
	    .withButtons([
	        {extend: 'csv'},
	        {extend: 'excel'},
	    ]);
        
    vm.dtColumns = [
        DTColumnBuilder.newColumn('id')
        	.withTitle('ID')
        	.withOption("width","10%"),
        DTColumnBuilder.newColumn('name')
        	.withTitle('Nombre')
        	.withOption("width","30%"),
        DTColumnBuilder.newColumn('description')
        	.withTitle('Descripción')
        	.withOption("width","30%"),
        DTColumnBuilder.newColumn(null)
        	.withTitle('Acciones')
        	.withOption("width","15%")
        	.withClass('column_text_center')
        	.notSortable()
            .renderWith(actionsHtml),
        DTColumnBuilder.newColumn(null)
        	.withTitle('Estado')
        	.withOption("width","15%")
        	.withClass('column_text_center')
        	.notSortable()
        	.renderWith(switchHtml)
    ];

    function editUser(person) {
        vm.message = 'You are trying to edit the row: ' + JSON.stringify(person.name);
        // Edit some data and call server to make changes...
        // Then reload the data so that DT is refreshed
        // vm.dtInstance.reloadData();
    }
    function activateUser(person) {
    	vm.message = 'You are activate row: ' + JSON.stringify(person.activate);
    }
    function createdRow(row, data, dataIndex) {
        // Recompiling so we can bind Angular directive to the DT
        $compile(angular.element(row).contents())($scope);
    }
    
    function actionsHtml(data, type, full, meta) {
        vm.persons[data.id] = data;
        return '<button type="button" class="btn btn-warning btn-circle" ng-click="showCase.editUser(showCase.persons[' + data.id + '])">' +
        '   <i class="fa fa-edit"></i>' +
        '</button>'
    }
    
    function switchHtml(data, type, full, meta) {
        vm.persons[data.id] = data;
        return '<input type="checkbox" class="js-switch" ' +
        'ui-switch="{size:' + "'small'" + ', color:' + "'#1AB394'" + '}" ' +
        'checked '+
        'ng-model="showCase.persons[' + data.id + '].activate"' +
        'ng-change="showCase.activateUser(showCase.persons[' + data.id + '])">' +
        /*'ng-true-value=1 ng-false-value=0 >' +*/
        '</input>'
        vm.dtInstance.reloadData();
    }
}

angular
    .module('inspinia')
    .controller('MainCtrl', MainCtrl)
    .controller('datatablesCtrl', datatablesCtrl)
    
    