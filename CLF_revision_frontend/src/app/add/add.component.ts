import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { ContactService } from '../contact.service';
import { Contact } from '../models';

@Component({
  selector: 'app-add',
  templateUrl: './add.component.html',
  styleUrls: ['./add.component.css']
})
export class AddComponent implements OnInit {

  contactForm !: FormGroup
  contactDetails !: FormArray
  
  updateForm !: FormGroup

  constructor(private fb : FormBuilder, private router: Router, private contactSvc : ContactService) { }

  ngOnInit(): void {
    this.contactDetails = this.fb.array([]);
    this.contactForm = this.fb.group({ contacts : this.contactDetails})
    
    this.updateForm = this.fb.group({
      id: this.fb.control <string>(this.contactSvc.contactObject.id!),
      name: this.fb.control<string> (this.contactSvc.contactObject.name),
      email: this.fb.control <string> (this.contactSvc.contactObject.email),
      mobile: this.fb.control <number> (this.contactSvc.contactObject.mobile)
    })
  }

  addContactRows(){
    const contactDetailGroup = this.fb.group({
      name : this.fb.control<string>(''),
      email : this.fb.control<string>(''),
      mobile: this.fb.control<number>(0)
    })
    this.contactDetails.push(contactDetailGroup);
  }

  async processForm(){
    const contact : Contact[] = this.contactForm.value as Contact[];
    console.log(">>> form object: >>>", contact);
    await this.contactSvc.addContactToDatabase(contact);
    this.router.navigate(['/list']);
  }

  //using observable method
  processForm1(){
    const contact : Contact[] = this.contactForm.value as Contact[];
    console.log(">>> form object: >>>", contact);
    this.contactSvc.addContact(contact).subscribe(data => {
      console.log(data);
      this.router.navigate(['/list']);
    })
  }

  async updateContactList(){
    const updatedContact : Contact = this.updateForm.value as Contact;
    console.log(">>>> updated form object: ", updatedContact);
    await this.contactSvc.updateContact(updatedContact);
    this.router.navigate(['/list']);
  }

}
