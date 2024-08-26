
// JavaScript to handle the menu toggle functionality
document.querySelectorAll('.trigger-menu').forEach(menu => {
 menu.addEventListener('click', function() {
 this.classList.toggle('expanded');
 this.nextElementSibling.style.maxHeight = this.classList.contains('expanded') ? this.nextElementSibling.scrollHeight + 'px' : '0px';
 this.nextElementSibling.style.opacity = this.classList.contains('expanded') ? '1' : '0';
     });
  });

