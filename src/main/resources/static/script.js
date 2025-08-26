document.addEventListener('DOMContentLoaded', () => {
    const urlInput = document.getElementById('urlInput');
    const qrColorPalette = document.getElementById('qrColorPalette');
    const bgColorPalette = document.getElementById('bgColorPalette');
    const generateBtn = document.getElementById('generateBtn');
    const qrCodeDisplay = document.querySelector('.qr-code-display');
    const qrCodeImage = document.getElementById('qrCodeImage');
    const downloadLink = document.getElementById('downloadLink');
    const errorBox = document.getElementById('errorBox');
    const errorMessage = document.getElementById('errorMessage');
    const infoBtn = document.getElementById('infoBtn');
    const modal = document.getElementById('modal');
    const closeBtn = document.querySelector('.close-btn');

    const stepTexts = document.querySelectorAll('.tutorial-step');

    const showError = (message) => {
        errorMessage.textContent = message;
        errorBox.style.display = 'block';
    };

    const hideError = () => {
        errorBox.style.display = 'none';
    };

    const handleColorSelection = (palette, event) => {
        if (!event.target.classList.contains('color-box')) return;
        palette.querySelectorAll('.color-box').forEach(box => {
            box.classList.remove('selected');
        });
        event.target.classList.add('selected');
    };

    const startTutorialAnimation = () => {
        stepTexts.forEach(step => {
            step.style.opacity = '0';
            step.style.transform = 'translateY(20px)';
        });

        setTimeout(() => {
            stepTexts[0].style.opacity = '1';
            stepTexts[0].style.transform = 'translateY(0)';
        }, 200);

        setTimeout(() => {
            stepTexts[1].style.opacity = '1';
            stepTexts[1].style.transform = 'translateY(0)';
        }, 600);

        setTimeout(() => {
            stepTexts[2].style.opacity = '1';
            stepTexts[2].style.transform = 'translateY(0)';
        }, 1000);
    };

    qrColorPalette.addEventListener('click', (event) => {
        handleColorSelection(qrColorPalette, event);
    });

    bgColorPalette.addEventListener('click', (event) => {
        handleColorSelection(bgColorPalette, event);
    });

    infoBtn.addEventListener('click', () => {
        modal.style.display = 'block';
        startTutorialAnimation();
    });

    closeBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    generateBtn.addEventListener('click', () => {
        hideError();
        qrCodeDisplay.style.display = 'none';

        const url = urlInput.value.trim();
        const qrColor = qrColorPalette.querySelector('.color-box.selected').getAttribute('data-color');
        const bgColor = bgColorPalette.querySelector('.color-box.selected').getAttribute('data-color');

        if (!url) {
            showError("Please enter a URL to generate the QR code.");
            return;
        }

        try {
            new URL(url);
        } catch (e) {
            showError("The entered value is not a valid URL. Please check the format.");
            return;
        }

        if (qrColor === bgColor) {
            showError("QR code color cannot be the same as the background color. Please select different colors.");
            return;
        }

        qrCodeDisplay.style.display = 'flex';

        const imageUrl = `http://localhost:8080/generate-qr?url=${encodeURIComponent(url)}&qrColor=${encodeURIComponent(qrColor)}&bgColor=${encodeURIComponent(bgColor)}`;
        qrCodeImage.src = imageUrl;

        const downloadUrl = `http://localhost:8080/download-qr?url=${encodeURIComponent(url)}&qrColor=${encodeURIComponent(qrColor)}&bgColor=${encodeURIComponent(bgColor)}`;
        downloadLink.href = downloadUrl;
        downloadLink.style.display = 'flex';
    });
});