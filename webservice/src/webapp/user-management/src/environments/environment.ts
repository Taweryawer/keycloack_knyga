// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  apiGetAllUsers: "http://localhost:8081/api/users/getAllUsers",
  apiGetUser: "http://localhost:8081/api/users/getUser",
  apiRoles: "http://localhost:8081/api/users/getAllRoles",
  apiRegister: "http://localhost:8081/api/registerUser",
  apiLogin: "http://localhost:8081/api/login",
  apiAddUser: "http://localhost:8081/api/users/addUser",
  apiEditUser: "http://localhost:8081/api/users/editUser",
  apiRemoveUser: "http://localhost:8081/api/users/removeUser",
  apiBooks: "http://localhost:8081/api/books",
  keycloakAuth: "http://localhost:8082/auth/realms/Knygers"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
