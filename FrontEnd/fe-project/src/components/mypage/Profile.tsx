import { useNavigate } from "react-router-dom";
import altUserImg from "../../assets/icons/altUserImg.png";

export default function Profile() {
  const navigate = useNavigate();

  const dummyUser = {
    nickname: "nick",
    imageUrl: "",
    mainSong: "songmain",
    songList: "list",
  };

  const pencilIcon = (
    <svg
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
        d="m16.862 4.487 1.687-1.688a1.875 1.875 0 1 1 2.652 2.652L10.582 16.07a4.5 4.5 0 0 1-1.897 1.13L6 18l.8-2.685a4.5 4.5 0 0 1 1.13-1.897l8.932-8.931Zm0 0L19.5 7.125M18 14v4.75A2.25 2.25 0 0 1 15.75 21H5.25A2.25 2.25 0 0 1 3 18.75V8.25A2.25 2.25 0 0 1 5.25 6H10"
      />
    </svg>
  );

  return (
    <>
      <div className="flex items-center w-[356px] h-[117px] mx-auto">
        <img
          src={dummyUser.imageUrl || altUserImg}
          className="w-28 h-28 rounded-full bg-gray-200 border-gray-400 border-2"
        />
        <div className="ml-6 flex-col">
          <div className="flex">
            <h1>My NickName</h1>
            <button className="ml-2" onClick={() => navigate("/change-name")}>
              {pencilIcon}
            </button>
          </div>
          {/* <p>my title song</p> */}

          <form className="max-w-sm mx-auto">
            <label
              htmlFor="countries"
              className="block mb-2 text-sm font-medium text-gray-900 dark:text-white"
            >
              Select an option
            </label>
            <select
              id="countries"
              defaultValue=""
              className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
            >
              <option value="" disabled>
                Choose a country
              </option>
              <option value="US">United States</option>
              <option value="CA">Canada</option>
              <option value="FR">France</option>
              <option value="DE">Germany</option>
            </select>
          </form>
        </div>
      </div>
      <hr className="mt-6 border-black" />
    </>
  );
}
