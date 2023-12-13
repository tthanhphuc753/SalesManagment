package com.example.Bookstore.Presentation.Controller.AdminController;


import com.example.Bookstore.Domain.Model.Book.Book;
import com.example.Bookstore.Domain.Model.Book.Categories;
import com.example.Bookstore.Presentation.Controller.BookController.BookController;
import com.example.Bookstore.Presentation.Controller.CategoriesController.CategoriesServiceController;
import com.example.Bookstore.Presentation.Controller.OrderController.OrderController;
import com.example.Bookstore.Presentation.Controller.userController.UserController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("admin")
@RequiredArgsConstructor
public class adminController {

    private final CategoriesServiceController categoriesController;
    private final BookController bookController;
    private final OrderController orderController;
    private final UserController userController;

    @GetMapping("/homepage")
    public String showAdminPage(Model model,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "15") int size) {
        bookController.pageSize(model, page, size);
        return "admin";
    }

    @GetMapping("book/update-form/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        bookController.showUpdateBookForm(model, id);
        return "updatebook";
    }

    @PostMapping("book/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute("book") Book newBook) {
        bookController.updateBook(id, newBook);
        return "redirect:/admin/homepage";
    }

    @GetMapping("book/add-form")
    public String showAddForm(Model model) {
        bookController.showAddBookForm(model);
        return "addbook";
    }


    @PostMapping("book/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookController.addBook(book);
        return "redirect:/admin/homepage";
    }

    @PostMapping("/books/delete/{id}")
    public String removeBook(@PathVariable Long id) {
        bookController.deleteBook(id);
        return "redirect:/admin/homepage";
    }

    @PostMapping("category/add")
    public String addCategory(@ModelAttribute("categories") Categories categories) {
        categoriesController.addCategories(categories);
        return "redirect:/admin/category/list";
    }

    @GetMapping("category/list")
    public String getAllCategory(Model model) {
        model.addAttribute("categories", categoriesController.getAllCategory());
        return "categorylist";
    }

    @GetMapping("order/list")
    public String getAllOrder(Model model) {
        model.addAttribute("orders", orderController.getAllOrder());
        return "orderlist";
    }
    @PostMapping("order/delete/{orderId}")
    public String deteleOrder(@PathVariable Long orderId)
    {
        orderController.deleteOrder(orderId);
        return "redirect:/admin/order/list";
    }

    @GetMapping("user/list")
    public String getAllUser(Model model) {
        model.addAttribute("user", userController.getAllUser());
        return "user";
    }

    @PostMapping("user/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        userController.deleteUser(userId);
        return "redirect:/admin/user/list";
    }

    @GetMapping("/search")
    public String searchBooksAdmin(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        List<Book> searchResults = bookController.searchBooks(keyword);
        model.addAttribute("books", searchResults);
        model.addAttribute("category", categoriesController.getAllCategory());
        return "admin";
    }

}
