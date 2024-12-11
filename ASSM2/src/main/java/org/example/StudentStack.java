package org.example;

class StudentStack {
    private Student[] stack;
    private int top;
    private int size;

    public StudentStack(int capacity) {
        stack = new Student[capacity];
        size = 0;
        top = -1;
    }

    public void push(Student student) {
        if (size == stack.length) {
            System.out.println("Stack overflow! Không thể thêm sinh viên.");
            return;
        }
        stack[++top] = student;
        size++;
    }

    public Student pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow! Không có sinh viên nào để loại bỏ.");
            return null;
        }
        Student student = stack[top--];
        size--;
        return student;
    }

    public Student peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return null;  // Trả về null nếu stack rỗng
        }
        return stack[top];  // Trả về sinh viên ở đỉnh stack
    }

    // Kiểm tra stack có rỗng không
    public boolean isEmpty() {
        return size == 0;
    }

    // Hiển thị các sinh viên trong stack
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return;
        }
        System.out.println("Sinh viên trong Stack:");
        for (int i = top; i >= 0; i--) {
            System.out.println(stack[i]);
        }
    }

    // Trả về kích thước hiện tại của stack
    public int size() {
        return size;
    }

    // Lấy sinh viên ở vị trí chỉ định
    public Student get(int index) {
        if (index >= 0 && index < size) {
            return stack[index];
        }
        return null;
    }

    // Cập nhật sinh viên ở vị trí chỉ định
    public void set(int index, Student student) {
        if (index >= 0 && index < size) {
            stack[index] = student;
        }
    }
}
