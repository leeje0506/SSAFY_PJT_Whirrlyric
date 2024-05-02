import altUserImg from "../../assets/icons/altUserImg.png";

export default function Profile() {
  const dummyUser = {
    nickname: "nick",
    imageUrl: "",
    mainSong: "songmain",
    songList: "list",
  };

  return (
    <>
    <div className="flex items-center w-[356px] h-[117px] mx-auto">
      <img
        src={dummyUser.imageUrl || altUserImg}
        className="w-28 h-28 rounded-full bg-gray-200 border-gray-400 border-2"
      />
      <div className="ml-6 flex-col">
        <h1>My Title Song</h1>
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
            <option value="" disabled>Choose a country</option>
            <option value="US">United States</option>
            <option value="CA">Canada</option>
            <option value="FR">France</option>
            <option value="DE">Germany</option>
          </select>
        </form>
      </div>
    </div>
    <hr className="mt-6 border-black"/>
    </>
  );
}