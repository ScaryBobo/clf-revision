import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ContactService } from '../contact.service';
import { Contact } from '../models';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  contactList : Contact[] = []

  contact !: Contact

  contactId : string = ""

  constructor(private contactSvc: ContactService, private router : Router) { }

  ngOnInit(): void {
    this.contactSvc.retrieveAllContacts().subscribe(x => this.contactList = x);
  }

  async asyncDeleteContact(idx: number){
    this.contactId = this.contactList.at(idx)?.id!;
    await this.contactSvc.deleteContact(this.contactId).toPromise();
    this.ngOnInit();
  }

  deleteContact(idx: number){
    this.contactId = this.contactList.at(idx)?.id!;
    this.contactSvc.deleteContact(this.contactId).subscribe(x=> {
      this.ngOnInit();
    });

  }

  updateContact(idx : number){
    this.contact = this.contactList.at(idx)!;
    this.contactSvc.contactObject = this.contact;
    console.log(">>> contact object to be updated: ", this.contactSvc.contactObject);
    this.router.navigate(['/add']);
  }



}
