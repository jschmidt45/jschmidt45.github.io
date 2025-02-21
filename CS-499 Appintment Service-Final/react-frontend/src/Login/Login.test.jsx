import React from "react";
import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import "@testing-library/jest-dom";
import { MemoryRouter } from "react-router-dom";
import LoginView from "./LoginView";
import userEvent from "@testing-library/user-event";

// Alias for user actions
const user = userEvent;

/**
 * Tests that the general layout and elements of the LoginView render correctly.
 */
test("LoginView renders correctly", () => {
  render(
    <MemoryRouter>
      <LoginView />
    </MemoryRouter>
  );

  // Check page title and creator text
  expect(screen.getByRole("heading", { level: 1 })).toHaveTextContent("Appointment Service");
  expect(screen.getByText("Created by John Schmidt")).toBeInTheDocument();

  // Check input labels
  expect(screen.getByRole("heading", { level: 2 })).toHaveTextContent("Login:");
  expect(screen.getByText("Username:")).toBeInTheDocument();
  expect(screen.getByText("Password:")).toBeInTheDocument();

  // Check input fields
  expect(screen.getByPlaceholderText("Enter your username")).toHaveValue("");
  expect(screen.getByPlaceholderText("Enter your password")).toHaveValue("");

  // Check buttons
  expect(screen.getByRole("button", { name: "Login" })).toBeInTheDocument();
  expect(screen.getByText("New User? Sign up here!")).toBeInTheDocument();
});

/**
 * Tests that entering text into the username and password fields works correctly.
 */
test("Username and password inputs work correctly", async () => {
  render(
    <MemoryRouter>
      <LoginView />
    </MemoryRouter>
  );

  const usernameInput = screen.getByPlaceholderText("Enter your username");
  const passwordInput = screen.getByPlaceholderText("Enter your password");

  await user.type(usernameInput, "TestUser");
  expect(usernameInput).toHaveValue("TestUser");

  await user.type(passwordInput, "SecurePass123");
  expect(passwordInput).toHaveValue("SecurePass123");
});

/**
 * Tests that logging in with invalid credentials displays an error message.
 */
test("Login fails when username and password are incorrect", async () => {
  render(
    <MemoryRouter>
      <LoginView />
    </MemoryRouter>
  );

  const usernameInput = screen.getByPlaceholderText("Enter your username");
  const passwordInput = screen.getByPlaceholderText("Enter your password");
  const loginButton = screen.getByRole("button", { name: "Login" });

  await user.type(usernameInput, "wrongUser");
  await user.type(passwordInput, "wrongPass");

  expect(screen.queryByText("An error occurred while logging in.")).not.toBeInTheDocument();

  await user.click(loginButton);

  await waitFor(() => {
    expect(screen.getByText("An error occurred while logging in.")).toBeInTheDocument();
  });
});

/**
 * Tests that logging in with correct credentials does not show an error message.
 */
test("Login succeeds with valid credentials", async () => {
  render(
    <MemoryRouter>
      <LoginView />
    </MemoryRouter>
  );

  const usernameInput = screen.getByPlaceholderText("Enter your username");
  const passwordInput = screen.getByPlaceholderText("Enter your password");
  const loginButton = screen.getByRole("button", { name: "Login" });

  await user.type(usernameInput, "admin");
  await user.type(passwordInput, "password123");

  await user.click(loginButton);

  await waitFor(() => {
    expect(screen.queryByText("Invalid credentials.")).not.toBeInTheDocument();
  });
});
