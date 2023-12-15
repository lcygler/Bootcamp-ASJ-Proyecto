import { Component } from '@angular/core';

@Component({
  selector: 'app-todolist',
  templateUrl: './todolist.component.html',
  styleUrls: ['./todolist.component.css'],
})
export class TodolistComponent {
  tasks: any[] = [];
  newTask: string = '';
  filter: string = 'All';
  idCounter: number = 1;

  addTask() {
    const isEmpty = this.newTask.trim() === '';

    if (!isEmpty) {
      const task = {
        id: this.idCounter,
        description: this.newTask,
        date: new Date(),
        done: false,
        deleted: false,
      };

      this.tasks.push(task);
      this.idCounter++;
      this.newTask = '';

      if (this.filter === 'Done' || this.filter === 'Deleted') {
        this.filter = 'All';
      }
    }
  }

  completeTask(task: any) {
    task.done = !task.done;
  }

  deleteTask(task: any) {
    const message = task.deleted
      ? 'Are you sure you want to permanently delete this task?'
      : 'Are you sure you want to delete this task?';

    const confirm = window.confirm(message);

    if (confirm) {
      if (task.deleted) {
        const index = this.tasks.indexOf(task);

        if (index !== -1) {
          this.tasks.splice(index, 1);
        }
      } else {
        task.deleted = true;
      }
    }
  }

  recoverTask(task: any) {
    task.deleted = false;
  }

  clearAll() {
    const confirm = window.confirm('Are you sure you want to clear all tasks?');

    if (confirm) {
      this.tasks.forEach((task) => (task.deleted = true));
      this.filter = 'All';
    }
  }

  deleteAll() {
    const confirm = window.confirm(
      'Are you sure you want to permanently delete all tasks?'
    );

    if (confirm) {
      this.tasks = this.tasks.filter((task) => !task.deleted);
      this.filter = 'All';
    }
  }

  showClearAll(): boolean {
    const hasUndeletedTasks: boolean = this.tasks.some((task) => !task.deleted);
    const notDeletedFilter: boolean = this.filter !== 'Deleted';
    return hasUndeletedTasks && notDeletedFilter;
  }

  showDeleteAll(): boolean {
    const hasDeletedTasks: boolean = this.tasks.some((task) => task.deleted);
    const deletedFilter: boolean = this.filter === 'Deleted';
    return hasDeletedTasks && deletedFilter;
  }

  updateFilter(selectedFilter: string) {
    this.filter = selectedFilter;
  }

  getFilteredTasks(): any[] {
    let filteredTasks: any[] = [...this.tasks];

    switch (this.filter) {
      case 'All':
        filteredTasks = filteredTasks.filter((task) => !task.deleted);
        break;

      case 'Pending':
        filteredTasks = filteredTasks.filter(
          (task) => !task.done && !task.deleted
        );
        break;

      case 'Done':
        filteredTasks = filteredTasks.filter(
          (task) => task.done && !task.deleted
        );
        break;

      case 'Deleted':
        filteredTasks = filteredTasks.filter((task) => task.deleted);
        break;
    }

    return filteredTasks.reverse();
  }
}
