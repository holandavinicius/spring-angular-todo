import { Component, OnInit } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { Task} from '../models/task.models';
import { CommonModule } from '@angular/common';
import { TodoCardComponent } from '../todo-card/todo-card.component';

@Component({
  selector: 'app-todo',
  imports: [FormsModule, CommonModule, TodoCardComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})

export class HomeComponent implements OnInit {
  taskArray: Task[] = [
    {
      title: 'Brush Teeth',
      status: 'To Do',
      description: 'Lorem Ipsum'
    },
    {
      title: 'Go to Gym',
      status: 'In Progress',
      description: 'Push day today'
    },
    {
      title: 'Finish Project',
      status: 'Done',
      description: 'Angular/Spring integration complete'
    }
  ];

  constructor(){}

  ngOnInit():void {}

  onSubmit(form: NgForm){

  }

  get toDoTasks(){
    return this.taskArray.filter(t => t.status === 'To Do');
  }

    get InProgressTasks(){
    return this.taskArray.filter(t => t.status === 'In Progress');
  }

    get DoneTasks(){
    return this.taskArray.filter(t => t.status === 'Done');
  }
}
