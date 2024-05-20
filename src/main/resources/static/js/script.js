console.log("Script loaded");

let currentTheme = getTheme();
// initial
document.addEventListener("DOMContentLoaded", () => {
  changeTheme();
});

// TODO
function changeTheme() {
  // Set to webpage
  changePageTheme(currentTheme, currentTheme);

  // set the listener to change theme button
  const changeThemeButton = document.querySelector("#theme_change_button");

  // get the current theme
  const oldTheme = currentTheme;
  changeThemeButton.addEventListener("click", (event) => {
    console.log("Change theme button clicked");
    // change the text of button
    if (currentTheme === "dark") {
      // theme to light
      currentTheme = "light";
    } else {
      // theme to dark
      currentTheme = "dark";
    }

    changePageTheme(currentTheme, oldTheme);
  });
}

// set theme to localstorage
function setTheme(theme) {
  localStorage.setItem("theme", theme);
}

// Get theme from localstorage
function getTheme() {
  let theme = localStorage.getItem("theme");
  return theme ? theme : "light";
}

// change current page theme
function changePageTheme(theme, oldTheme) {
  // update localstorage
  setTheme(theme);
  //remove the current theme
  document.querySelector("html").classList.remove(oldTheme);
  // set the current theme
  document.querySelector("html").classList.add(theme);
  // change the text of button
  document
    .querySelector("#theme_change_button")
    .querySelector("span").textContent = theme == "light" ? "Dark" : "Light";
}
