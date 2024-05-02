export default function ChangeName() {
  return (
    <div className="flex flex-col">
      <h1 className="mx-auto mb-6">변경할 닉네임을 입력해 주세요.</h1>
      <div className="mb-6">
        <input
          type="text"
          id="default-input"
          className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
        />
      </div>
      <label htmlFor="input-caution" className="flex flex-col items-center">
        <span className=" mb-2 text-sm font-medium text-gray-900 dark:text-white">
          닉네임은 최대 10자까지 가능해요.
        </span>
        <span className=" mb-2 text-sm font-medium text-gray-900 dark:text-white">
          알파벳 대소문자, 한글, 숫자만 가능해요.
        </span>
      </label>
    </div>
  );
}
