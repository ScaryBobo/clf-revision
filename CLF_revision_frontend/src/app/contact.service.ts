import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, ɵɵsetComponentScope } from '@angular/core';
import { firstValueFrom, lastValueFrom, Observable } from 'rxjs';
import { Contact } from './models';


@Injectable({
  providedIn: 'root'
})
export class ContactService {

  constructor(private http: HttpClient) { }

  deleteUri : string = ""

  private _contact !: Contact
  set contactObject (contact : Contact){
    this._contact = contact;
  }
  get contactObject() : Contact {
    return this._contact;
  }

  //using promises
  addContactToDatabase(contact : Contact[]){
    console.log("add contact service is called! ");
    const headers = new HttpHeaders()
      .set('Content-type', 'application/json')
      .set('Accept', 'application/json')

      return firstValueFrom(
        this.http.post<any>('/create', contact, {headers})
      )
  }

//Using observable
  addContact(contact: Contact[]): Observable<any>{
    console.log("add contact via observable method is called!");
    const headers = new HttpHeaders()
      .set('Content-type', 'application/json')
      .set('Accept', 'application/json')
    
    return this.http.post('/create', contact, {headers});
  }

  retrieveAllContacts() : Observable<Contact[]>{
    return this.http.get<Contact[]>('/retrieve');
  }

  deleteContact(contactId : string){
    console.log("delete is called! ");
    this.deleteUri = ('/delete/' + contactId);
    console.log("delete URI is >>>>>>>>>" , this.deleteUri);
    return this.http.delete('/delete/' + contactId);
  }

  updateContact(contactUpdate : Contact){
    console.log("update service is called! ")
    const headers = new HttpHeaders()
      .set('Content-type', 'application/json')
      .set('Accept', 'application/json')

    return firstValueFrom(
      this.http.put<any>('/update', contactUpdate, {headers})
    )
  }



}
