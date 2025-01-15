document.addEventListener('DOMContentLoaded', () => {
  // Theme Toggle
  const themeToggle = document.querySelector('.theme-toggle');
  const savedTheme = localStorage.getItem('theme') || 'light';
  
  // Initialize theme
  document.documentElement.setAttribute('data-theme', savedTheme);
  document.body.setAttribute('data-theme', savedTheme);

  themeToggle?.addEventListener('click', () => {
      const currentTheme = document.documentElement.getAttribute('data-theme');
      const newTheme = currentTheme === 'dark' ? 'light' : 'dark';
      
      // Update both documentElement and body
      document.documentElement.setAttribute('data-theme', newTheme);
      document.body.setAttribute('data-theme', newTheme);
      localStorage.setItem('theme', newTheme);
  });

  // Cursor Management
  const cursor = document.querySelector('.custom-cursor');
  const interactiveElements = document.querySelectorAll('a, button, [role="button"], input[type="submit"]');

  if (cursor && window.matchMedia('(hover: hover)').matches) {
      let cursorVisible = false;
      let rafId = null;

      const onMouseMove = (e) => {
          if (rafId) {
              cancelAnimationFrame(rafId);
          }

          rafId = requestAnimationFrame(() => {
              cursor.style.left = `${e.clientX}px`;
              cursor.style.top = `${e.clientY}px`;
              
              if (!cursorVisible) {
                  cursor.style.opacity = '1';
                  cursorVisible = true;
              }
          });
      };

      const onMouseLeave = () => {
          cursor.style.opacity = '0';
          cursorVisible = false;
      };

      const onMouseEnter = () => {
          cursor.style.opacity = '1';
          cursorVisible = true;
      };

      // Add event listeners
      document.addEventListener('mousemove', onMouseMove);
      document.addEventListener('mouseleave', onMouseLeave);
      document.addEventListener('mouseenter', onMouseEnter);

      // Interactive elements cursor effects
      interactiveElements.forEach(el => {
          el.addEventListener('mouseenter', () => {
              cursor.classList.add('hover');
          });
          
          el.addEventListener('mouseleave', () => {
              cursor.classList.remove('hover');
          });
      });
  } else if (cursor) {
      cursor.remove();
  }

  // Device detection and orientation handling
  function detectDevice() {
      const width = window.innerWidth;
      const height = window.innerHeight;
      const orientation = width > height ? 'landscape' : 'portrait';

      document.body.setAttribute('data-orientation', orientation);

      const devices = document.querySelectorAll('.device');
      devices.forEach(device => device.style.display = 'none');

      if (width < 768) {
          document.querySelector('.phone-mockup').style.display = 'block';
      } else if (width < 1200) {
          document.querySelector('.tablet-mockup').style.display = 'block';
      } else {
          document.querySelector('.desktop-mockup').style.display = 'block';
      }
  }

  // Initialize device detection
  detectDevice();
  window.addEventListener('resize', detectDevice);
});