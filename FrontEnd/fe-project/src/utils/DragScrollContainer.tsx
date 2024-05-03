import React, { useRef, useEffect } from "react";

interface DragScrollContainerProps {
  children: React.ReactNode;
  addClass?: string;
}

const DragScrollContainer: React.FC<DragScrollContainerProps> = ({ children, addClass }) => {
  const scrollRef = useRef<HTMLDivElement>(null);

  useEffect(() => {
    const scrollContainer = scrollRef.current;
    if (!scrollContainer) return;

    let isDragging = false;
    let startPos = 0;
    let scrollLeft = 0;

    const startDragging = (e: MouseEvent) => {
      isDragging = true;
      startPos = e.pageX - scrollContainer.offsetLeft;
      scrollLeft = scrollContainer.scrollLeft;
    };

    const stopDragging = () => {
      isDragging = false;
    };

    const whileDragging = (e: MouseEvent) => {
      if (!isDragging) return;
      e.preventDefault();
      const x = e.pageX - scrollContainer.offsetLeft;
      const walk = x - startPos;
      scrollContainer.scrollLeft = scrollLeft - walk;
    };

    scrollContainer.addEventListener("mousedown", startDragging);
    window.addEventListener("mouseup", stopDragging);
    scrollContainer.addEventListener("mousemove", whileDragging);

    return () => {
      scrollContainer.removeEventListener("mousedown", startDragging);
      window.removeEventListener("mouseup", stopDragging);
      scrollContainer.removeEventListener("mousemove", whileDragging);
    };
  }, []);

  return (
    <div ref={scrollRef} className={`overflow-auto no-scroll cursor-pointer ${addClass || ''}`}>
      {children}
    </div>
  );
};

export default DragScrollContainer;
