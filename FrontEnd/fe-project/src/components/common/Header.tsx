import { useLocation, useNavigate } from "react-router-dom";

export default function Header() {
  const location = useLocation();
  const navigate = useNavigate();

  const leftArrow = <svg
  xmlns="http://www.w3.org/2000/svg"
  fill="none"
  viewBox="0 0 24 24"
  strokeWidth={1.5}
  stroke="currentColor"
  className="w-6 h-6"
>
  <path
    strokeLinecap="round"
    strokeLinejoin="round"
    d="M10.5 19.5 3 12m0 0 7.5-7.5M3 12h18"
  />
</svg>

  const getHeaderName = (pathname: string) => {
    switch (pathname) {
      case "/write-song":
        return "Write a Song";
      case "/play-song":
        return "Play Song";
      case "/mypage":
        return "My Page";
    }
  };

  return (
    <div className="flex items-center w-header h-header mt-12 mx-auto px-4">
      <button className="text-left" onClick={() => navigate(-1)}>
        {leftArrow}  
      </button>
      <h1 className="flex-grow text-center">
        {getHeaderName(location.pathname)}
      </h1>
    </div>
  );
}
