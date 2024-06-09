// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  APIWarehouses: "http://localhost:8080/api/v1/warehouses/",
  APIDeliveries: "http://localhost:8080/api/v1/deliveries/",
  APIPlannings: "http://localhost:8080/api/v1/plannings/",
  APICreatePlannings: "http://localhost:8080/planning",
  APIPackagings: "http://localhost:8080/api/v1/logistics/",
  APIRoutes: "http://localhost:8080/api/v1/routes/",
  APITrucks: "http://localhost:8080/api/v1/trucks/",
  APIUsers: "http://localhost:8080/api/v1/user",
  APIPassword: "http://localhost:8080/api/v1/users/password/",
  APIDeleteUser: "http://localhost:8080/api/v1/users/cancel/",
  APIRoles: "http://localhost:8080/api/v1/roles/",
  twoFactorAuthenticationEnabled: true
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
