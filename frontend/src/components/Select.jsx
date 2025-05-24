import React from 'react';

const Select = ({
  label,
  name,
  value,
  onChange,
  options,
  error,
  placeholder,
  required = false,
  disabled = false,
  className = '',
  ...props
}) => {
  const baseClasses = 'block w-full rounded-md shadow-sm sm:text-sm';
  const stateClasses = disabled
    ? 'bg-gray-100 text-gray-500 cursor-not-allowed'
    : 'focus:ring-blue-500 focus:border-blue-500 border-gray-300';
  const errorClasses = error
    ? 'border-red-300 text-red-900 placeholder-red-300 focus:ring-red-500 focus:border-red-500'
    : '';

  const selectClasses = `${baseClasses} ${stateClasses} ${errorClasses} ${className}`;

  return (
    <div>
      {label && (
        <label
          htmlFor={name}
          className="block text-sm font-medium text-gray-700 mb-1"
        >
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </label>
      )}
      <select
        id={name}
        name={name}
        value={value}
        onChange={onChange}
        disabled={disabled}
        className={selectClasses}
        {...props}
      >
        {placeholder && (
          <option value="" disabled>
            {placeholder}
          </option>
        )}
        {options.map((option) => (
          <option key={option.value} value={option.value}>
            {option.label}
          </option>
        ))}
      </select>
      {error && (
        <p className="mt-1 text-sm text-red-600" id={`${name}-error`}>
          {error}
        </p>
      )}
    </div>
  );
};

export default Select; 