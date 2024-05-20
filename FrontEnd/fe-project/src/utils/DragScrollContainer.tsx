// import React, { useRef, useEffect } from "react";

// interface DragScrollContainerProps {
//   children: React.ReactNode;
//   addClass?: string;
// }

// const DragScrollContainer: React.FC<DragScrollContainerProps> = ({ children, addClass }) => {
//   const scrollRef = useRef<HTMLDivElement>(null);

//   useEffect(() => {
//     const scrollContainer = scrollRef.current;
//     if (!scrollContainer) return;

//     let isDragging = false;
//     let startPos = 0;
//     let scrollLeft = 0;

//     const startDragging = (e: MouseEvent) => {
//       isDragging = true;
//       startPos = e.pageX - scrollContainer.offsetLeft;
//       scrollLeft = scrollContainer.scrollLeft;
//     };

//     const stopDragging = () => {
//       isDragging = false;
//     };

//     const whileDragging = (e: MouseEvent) => {
//       if (!isDragging) return;
//       e.preventDefault();
//       const x = e.pageX - scrollContainer.offsetLeft;
//       const walk = x - startPos;
//       scrollContainer.scrollLeft = scrollLeft - walk;
//     };

//     scrollContainer.addEventListener("mousedown", startDragging);
//     window.addEventListener("mouseup", stopDragging);
//     scrollContainer.addEventListener("mousemove", whileDragging);

//     return () => {
//       scrollContainer.removeEventListener("mousedown", startDragging);
//       window.removeEventListener("mouseup", stopDragging);
//       scrollContainer.removeEventListener("mousemove", whileDragging);
//     };
//   }, []);

//   return (
//     <div ref={scrollRef} className={`overflow-auto no-scroll cursor-pointer ${addClass || ''}`}>
//       {children}
//     </div>
//   );
// };

// export default DragScrollContainer;

import React, { useRef, useEffect } from "react";

interface DragScrollContainerProps {
  children: React.ReactNode;
  addClass?: string;
}

const DragScrollContainer: React.FC<DragScrollContainerProps> = ({ children, addClass }) => {
  const scrollRef = useRef<HTMLDivElement>(null);
  const isDraggedRef = useRef(false);

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
      isDraggedRef.current = false;
    };

    const stopDragging = () => {
      isDragging = false;
      if (isDraggedRef.current) {
        setTimeout(() => { isDraggedRef.current = false; }, 0);
      }
    };

    const whileDragging = (e: MouseEvent) => {
      if (!isDragging) return;
      e.preventDefault();
      const x = e.pageX - scrollContainer.offsetLeft;
      const walk = x - startPos;
      scrollContainer.scrollLeft = scrollLeft - walk;
      isDraggedRef.current = true;
    };

    const handleClick = (e: MouseEvent) => {
      if (isDraggedRef.current) {
        e.stopPropagation();
        e.preventDefault();
      }
    };

    scrollContainer.addEventListener("mousedown", startDragging);
    window.addEventListener("mouseup", stopDragging);
    scrollContainer.addEventListener("mousemove", whileDragging);
    scrollContainer.addEventListener("click", handleClick, true);

    return () => {
      scrollContainer.removeEventListener("mousedown", startDragging);
      window.removeEventListener("mouseup", stopDragging);
      scrollContainer.removeEventListener("mousemove", whileDragging);
      scrollContainer.removeEventListener("click", handleClick, true);
    };
  }, []);

  return (
    <div ref={scrollRef} className={`overflow-auto no-scroll cursor-pointer ${addClass || ''}`}>
      {children}
    </div>
  );
};

export default DragScrollContainer;
