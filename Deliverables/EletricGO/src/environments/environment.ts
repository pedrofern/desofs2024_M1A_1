// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  // APIWarehouses: "http://localhost:5000/api/warehouses/",
  // APIDeliveries: "http://localhost:5000/api/deliveries/",
  // APIPlannings: "http://localhost:3000/api/plannings/",
  // APIPackagings: "http://localhost:3000/api/packagings/",
  // APIRoutes: "http://localhost:3000/api/routes/",
  // APITrucks: "http://localhost:3000/api/trucks/",
  APIWarehouses: "https://lapr5g79ga.azurewebsites.net/api/warehouses/",
  APIDeliveries: "https://lapr5g79ga.azurewebsites.net/api/deliveries/",
  APIPlannings: "http://localhost:3000/api/plannings/",
  //APICreatePlannings: "http://10.9.23.98:6969/planning",
  APICreatePlannings: "http://localhost:6969/planning",
  APIPackagings: "http://localhost:3000/api/packagings/",
  APIRoutes: "http://localhost:3000/api/routes/",
  APITrucks: "http://localhost:3000/api/trucks/",
  APILogin: "http://localhost:3000/api/auth/",
  APIUsers: "http://localhost:3000/api/users/",
  APIPassword: "http://localhost:3000/api/users/password/",
  APIDeleteUser: "http://localhost:3000/api/users/cancel/",
  APIRoles: "http://localhost:3000/api/roles/"
  
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
