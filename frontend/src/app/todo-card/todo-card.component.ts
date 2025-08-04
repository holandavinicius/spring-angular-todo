import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Task } from '../models/task.models';


@Component({
  selector: 'app-todo-card',
  imports: [CommonModule],
  templateUrl: './todo-card.component.html',
  styleUrl: './todo-card.component.css'
})
export class TodoCardComponent {
  @Input() task!: Task;
}
