import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserListComponent} from "./user-list/user.component";
import {LoginComponent} from "./login/login.component";
import {AuthGuard} from "./guards/auth.guard";
import {AdminGuard} from "./guards/admin.guard";
import {HomepageComponent} from "./homepage/homepage.component";
import {AdminAddUserComponent} from "./admin-add-user/admin-add-user.component";
import {AdminEditUserComponent} from "./admin-edit-user/admin-edit-user.component";
import {UserGuard} from "./guards/user.guard";
import {BookPageComponent} from "./book-page/book-page.component";

const routes: Routes = [
  { path: 'admin', canActivateChild: [AuthGuard], children: [
    { path: '', component: UserListComponent, canActivate: [AdminGuard] },
    { path: 'addUser', component: AdminAddUserComponent, canActivate: [AdminGuard] },
    { path: 'edit', component: AdminEditUserComponent, canActivate: [AdminGuard]}
  ]},
  { path: 'login', component: LoginComponent },
  { path: '', redirectTo: '/1', pathMatch: 'full' },
  { path: ':page', component: HomepageComponent },
  { path: 'book', children: [
      { path: ':id', component: BookPageComponent}
    ]
  },
  { path: '**', redirectTo: ''}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
